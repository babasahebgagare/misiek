start = clock;
%input = fopen('input', 'r');
[inputfile, outputfile, lam, maxits, convits, p, kind] =textread('./input', '%s %s %f %d %d %f %s', 1);
plt = 0; details=1; nonoise = 1;
%[lam, maxits, p, k] = textread('./input', '%f %d %f %s', 1);
%fclose(input);
inputfile{:}
outputfile{:}
lam
maxits
p
kind

i=1;

s = load(inputfile{:});


if lam>0.9
    fprintf('\n*** Warning: Large damping factor in use. Turn on plotting\n');
    fprintf('    to monitor the net similarity. The algorithm will\n');
    fprintf('    change decisions slowly, so consider using a larger value\n');
    fprintf('    of convits.\n\n');
end;

% Check that standard arguments are consistent in size
if length(size(s))~=2 error('s should be a 2D matrix');
elseif length(size(p))>2 error('p should be a vector or a scalar');
elseif size(s,2)==3
    tmp=max(max(s(:,1)),max(s(:,2)));
    if length(p)==1 N=tmp; else N=length(p); end;
    if tmp>N
        error('data point index exceeds number of data points');
    elseif min(min(s(:,1)),min(s(:,2)))<=0
        error('data point indices must be >= 1');
    end;
elseif size(s,1)==size(s,2)
    N=size(s,1);
    if (length(p)~=N)&&(length(p)~=1)
        error('p should be scalar or a vector of size N');
    end;
else error('s must have 3 columns or be square'); end;

% Make vector of preferences
if length(p)==1 p=p*ones(N,1); end;

% Append self-similarities (preferences) to s-matrix
tmps=[repmat([1:N]',[1,2]),p]; s=[s;tmps];
M=size(s,1);

% In case user did not remove degeneracies from the input similarities,
% avoid degenerate solutions by adding a small amount of noise to the
% input similarities
if ~nonoise
    rns=randn('state'); randn('state',0);
    s(:,3)=s(:,3)+(eps*s(:,3)+realmin*100).*rand(M,1);
    randn('state',rns);
end;

% Construct indices of neighbors
ind1e=zeros(N,1);
for j=1:M k=s(j,1); ind1e(k)=ind1e(k)+1; end;
ind1e=cumsum(ind1e); ind1s=[1;ind1e(1:end-1)+1]; ind1=zeros(M,1); 
for j=1:M k=s(j,1); ind1(ind1s(k))=j; ind1s(k)=ind1s(k)+1; end;
ind1s=[1;ind1e(1:end-1)+1];
ind2e=zeros(N,1);
for j=1:M k=s(j,2); ind2e(k)=ind2e(k)+1; end;
ind2e=cumsum(ind2e); ind2s=[1;ind2e(1:end-1)+1]; ind2=zeros(M,1); 
for j=1:M k=s(j,2); ind2(ind2s(k))=j; ind2s(k)=ind2s(k)+1; end;
ind2s=[1;ind2e(1:end-1)+1];

% Allocate space for messages, etc
A=zeros(M,1); R=zeros(M,1); t=1;
if plt netsim=zeros(1,maxits+1); end;
if details
    idx=zeros(N,maxits+1);
    netsim=zeros(1,maxits+1); 
    dpsim=zeros(1,maxits+1); 
    expref=zeros(1,maxits+1); 
end;

% Execute parallel affinity propagation updates
e=zeros(N,convits); dn=0; i=0;
while ~dn
    i=i+1; 

    % Compute responsibilities
    for j=1:N
        ss=s(ind1(ind1s(j):ind1e(j)),3);
        as=A(ind1(ind1s(j):ind1e(j)))+ss;
        [Y,I]=max(as); as(I)=-realmax; [Y2,I2]=max(as);
        r=ss-Y; r(I)=ss(I)-Y2;
        R(ind1(ind1s(j):ind1e(j)))=(1-lam)*r+ ...
            lam*R(ind1(ind1s(j):ind1e(j)));
    end;

    % Compute availabilities
    for j=1:N
        rp=R(ind2(ind2s(j):ind2e(j)));
        rp(1:end-1)=max(rp(1:end-1),0);
        a=sum(rp)-rp;
        a(1:end-1)=min(a(1:end-1),0);
        A(ind2(ind2s(j):ind2e(j)))=(1-lam)*a+ ...
            lam*A(ind2(ind2s(j):ind2e(j)));
    end;

    % Check for convergence
    E=((A(M-N+1:M)+R(M-N+1:M))>0); e(:,mod(i-1,convits)+1)=E; K=sum(E);
    if i>=convits || i>=maxits
        se=sum(e,2);
        unconverged=(sum((se==convits)+(se==0))~=N);
        if (~unconverged&&(K>0))||(i==maxits) dn=1; end;
    end;

    % Handle plotting and storage of details, if requested
    if plt||details
        if K==0
            tmpnetsim=nan; tmpdpsim=nan; tmpexpref=nan; tmpidx=nan;
        else
            tmpidx=zeros(N,1); tmpdpsim=0;
            tmpidx(find(E))=find(E); tmpexpref=sum(p(find(E)));
            discon=0;
            for j=find(E==0)'
                ss=s(ind1(ind1s(j):ind1e(j)),3);
                ii=s(ind1(ind1s(j):ind1e(j)),2);
                ee=find(E(ii));
                if length(ee)==0 discon=1;
                else
                    [smx imx]=max(ss(ee));
                    tmpidx(j)=ii(ee(imx));
                    tmpdpsim=tmpdpsim+smx;
                end;
            end;
            if discon
                tmpnetsim=nan; tmpdpsim=nan; tmpexpref=nan; tmpidx=nan;
            else tmpnetsim=tmpdpsim+tmpexpref;
            end;
        end;
    end;
    if details
        netsim(i)=tmpnetsim; dpsim(i)=tmpdpsim; expref(i)=tmpexpref;
        idx(:,i)=tmpidx;
    end;
    if plt
        netsim(i)=tmpnetsim;
        figure(234); 
        tmp=1:i; tmpi=find(~isnan(netsim(1:i)));
        plot(tmp(tmpi),netsim(tmpi),'r-');
        xlabel('# Iterations');
        ylabel('Net similarity of quantized intermediate solution');
        drawnow;
    end;
end;
% Identify  exemplars
kind
E=((A(M-N+1:M)+R(M-N+1:M))>0); K=sum(E);
E
if K>0
        
    tmpidx=zeros(N,1); tmpidx(find(E))=find(E); % Identify clusters
    for j=find(E==0)'
        ss=s(ind1(ind1s(j):ind1e(j)),3);
        ii=s(ind1(ind1s(j):ind1e(j)),2);
        ee=find(E(ii));
        [smx imx]=max(ss(ee));
        if length(ii(ee(imx)))>0
            tmpidx(j)=ii(ee(imx));
        else
            tmpidx(j)=0;
        end;
    end;
    tmpidx
    EE=zeros(N,1);
    for j=find(E)'
        jj=find(tmpidx==j); mx=-Inf;
        ns=zeros(N,1); msk=zeros(N,1);
        for m=jj'
            mm=s(ind1(ind1s(m):ind1e(m)),2);
            msk(mm)=msk(mm)+1;
            ns(mm)=ns(mm)+s(ind1(ind1s(m):ind1e(m)),3);
        end;
        ii=jj(find(msk(jj)==length(jj)));
        [smx imx]=max(ns(ii));
        EE(ii(imx))=1;
    end;
    E=EE;
    tmpidx=zeros(N,1); tmpdpsim=0;
    tmpidx(find(E))=find(E); tmpexpref=sum(p(find(E)));
    for j=find(E==0)'
        ss=s(ind1(ind1s(j):ind1e(j)),3);
        ii=s(ind1(ind1s(j):ind1e(j)),2);
        ee=find(E(ii));
        [smx imx]=max(ss(ee));
        if length(ii(ee(imx)))>0
            tmpidx(j)=ii(ee(imx));
        else
            tmpidx(j)=0;
        end;       
        tmpdpsim=tmpdpsim+smx;
    end;
    tmpnetsim=tmpdpsim+tmpexpref;
else
    tmpidx=nan*ones(N,1); tmpnetsim=nan; tmpexpref=nan;
end;

tmpidx
        
fileout = fopen(outputfile{:}, 'w');
    if strcmp(kind{:},'clusters')
        kind
        clust = tmpidx;
    else
        kind
        clust = find(E);
    end;
    %clust
    for j=1:size(clust), fprintf(fileout, '%d\n', clust(j)); end;
    fclose(fileout)

if details
    netsim(i+1)=tmpnetsim; netsim=netsim(1:i+1);
    dpsim(i+1)=tmpnetsim-tmpexpref; dpsim=dpsim(1:i+1);
    expref(i+1)=tmpexpref; expref=expref(1:i+1);
    idx(:,i+1)=tmpidx; idx=idx(:,1:i+1);
else
    netsim=tmpnetsim; dpsim=tmpnetsim-tmpexpref;
    expref=tmpexpref; idx=tmpidx;
end;


if plt||details
    fprintf('\nNumber of identified clusters: %d\n',K);
    fprintf('Fitness (net similarity): %f\n',tmpnetsim);
    fprintf('  Similarities of data points to exemplars: %f\n',dpsim(end));
    fprintf('  Preferences of selected exemplars: %f\n',tmpexpref);
    fprintf('Number of iterations: %d\n\n',i);
end;
if unconverged
    fprintf('\n*** Warning: Algorithm did not converge. The similarities\n');
    fprintf('    may contain degeneracies - add noise to the similarities\n');
    fprintf('    to remove degeneracies. To monitor the net similarity,\n');
    fprintf('    activate plotting. Also, consider increasing maxits and\n');
    fprintf('    if necessary dampfact.\n\n');
end;
