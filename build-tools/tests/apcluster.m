start = clock;
%input = fopen('input', 'r');
[inputfile, outputfile, lam, maxits, convits, p, kind] =textread('./input', '%s %s %f %d %d %f %s', 1);
plt = 0; details=0; nonoise = 1;
%[lam, maxits, p, k] = textread('./input', '%f %d %f %s', 1);
%fclose(input);
inputfile{:}
outputfile{:}
lam
maxits
p
kind

%maxits=5; convits=100; lam=0.5; plt=0; details=0; nonoise=1; p=-4500;
i=1;
%inputfile = 'TravelRoutingSimilarities.txt';
%inputfile = 'ToyProblemSimilarities.txt';
%outputfile = 'travelAff.out';

s = load(inputfile{:}); 


% Check that standard arguments are consistent in size
if length(size(s))~=2 error('s should be a 2D matrix');
elseif size(s,2)==3
    tmp=max(max(s(:,1)),max(s(:,2)));
    N=tmp;
elseif size(s,1)==size(s,2)
    N=size(s,1);
else error('s must have 3 columns or be square'); end;

if size(s,2)==3 && size(s,1)~=3,
    S=-Inf*ones(N,N,class(s)); 
    for j=1:size(s,1), S(s(j,1),s(j,2))=s(j,3); end;
else S=s;
end;

for j=1:N, S(j,j) = p; end;
S(2,2)

if S==S', symmetric=true; else symmetric=false; end;
realmin_=realmin(class(s)); realmax_=realmax(class(s));

% In case user did not remove degeneracies from the input similarities,
% avoid degenerate solutions by adding a small amount of noise to the
% input similarities
if ~nonoise
    rns=randn('state'); randn('state',0);
    S=S+(eps*S+realmin_*100).*rand(N,N);
    randn('state',rns);
end;

% Place preferences on the diagonal of S
%if length(p)==1 for i=1:N S(i,i)=p; end;
%else for i=1:N S(i,i)=p(i); end;
%end;

% Numerical stability -- replace -INF with -realmax
n=find(S<-realmax_); if ~isempty(n), warning('-INF similarities detected; changing to -REALMAX to ensure numerical stability'); S(n)=-realmax_; end; clear('n');
if ~isempty(find(S>realmax_,1)), error('+INF similarities detected; change to a large positive value (but smaller than +REALMAX)'); end;


% Allocate space for messages, etc
dS=diag(S); A=zeros(N,N,class(s)); R=zeros(N,N,class(s)); t=1;
if plt, netsim=zeros(1,maxits+1); end;
if details
    idx=zeros(N,maxits+1);
    netsim=zeros(1,maxits+1); 
    dpsim=zeros(1,maxits+1); 
    expref=zeros(1,maxits+1); 
end;

% Execute parallel affinity propagation updates
e=zeros(N,convits); dn=0; i=0;
if symmetric, ST=S; else ST=S'; end; % saves memory if it's symmetric
while ~dn
    i=i+1; 

    % Compute responsibilities
	A=A'; R=R';
	for ii=1:N,
		old = R(:,ii);
		AS = A(:,ii) + ST(:,ii); [Y,I]=max(AS); AS(I)=-Inf;
		[Y2,I2]=max(AS);
		R(:,ii)=ST(:,ii)-Y;
		R(I,ii)=ST(I,ii)-Y2;
		R(:,ii)=(1-lam)*R(:,ii)+lam*old; % Damping
        R(R(:,ii)>realmax_,ii)=realmax_;
	end;
	A=A'; R=R';

    % Compute availabilities
	for jj=1:N,
		old = A(:,jj);
		Rp = max(R(:,jj),0); Rp(jj)=R(jj,jj);
		A(:,jj) = sum(Rp)-Rp;
		dA = A(jj,jj); A(:,jj) = min(A(:,jj),0); A(jj,jj) = dA;
		A(:,jj) = (1-lam)*A(:,jj) + lam*old; % Damping
	end;
	
    % Check for convergence
    E=((diag(A)+diag(R))>0); e(:,mod(i-1,convits)+1)=E; K=sum(E);
    if i>=convits || i>=maxits,
        se=sum(e,2);
        unconverged=(sum((se==convits)+(se==0))~=N);
        if (~unconverged&&(K>0))||(i==maxits) dn=1; end;
    end;

    % Handle plotting and storage of details, if requested
    if plt||details
        if K==0
            tmpnetsim=nan; tmpdpsim=nan; tmpexpref=nan; tmpidx=nan;
        else
            I=find(E); notI=find(~E); [tmp c]=max(S(:,I),[],2); c(I)=1:K; tmpidx=I(c);
            tmpdpsim=sum(S(sub2ind([N N],notI,tmpidx(notI))));
            tmpexpref=sum(dS(I));
            tmpnetsim=tmpdpsim+tmpexpref;
        end;
    end;
    if details
        netsim(i)=tmpnetsim; dpsim(i)=tmpdpsim; expref(i)=tmpexpref;
        idx(:,i)=tmpidx;
    end;
    if plt,
        netsim(i)=tmpnetsim;
		figure(234);
        plot(((netsim(1:i)/10)*100)/10,'r-'); xlim([0 i]); % plot barely-finite stuff as infinite
        xlabel('# Iterations');
        ylabel('Fitness (net similarity) of quantized intermediate solution');
%         drawnow; 
    end;
end; % iterations
I=find((diag(A)+diag(R))>0); K=length(I); % Identify exemplars

fileout = fopen(outputfile{:}, 'w');

if K>0
    [tmp c]=max(S(:,I),[],2); c(I)=1:K; % Identify clusters
    % Refine the final set of exemplars and clusters and return results

    for k=1:K ii=find(c==k); [y j]=max(sum(S(ii,ii),1)); I(k)=ii(j(1)); end; notI=reshape(setdiff(1:N,I),[],1);
    [tmp c]=max(S(:,I),[],2); c(I)=1:K; tmpidx=I(c);

    if strcmp(kind{:},'clusters')
        kind
        clust = I(c);
    else
        kind
        clust = I
    end;
    %clust
    for j=1:size(clust), fprintf(fileout, '%d\n', clust(j)); end;
    fclose(fileout)

	tmpdpsim=sum(S(sub2ind([N N],notI,tmpidx(notI))));
	tmpexpref=sum(dS(I));
	tmpnetsim=tmpdpsim+tmpexpref;
else
    tmpidx=nan*ones(N,1); tmpnetsim=nan; tmpexpref=nan;
end;
if details
    netsim(i+1)=tmpnetsim; netsim=netsim(1:i+1);
    dpsim(i+1)=tmpdpsim; dpsim=dpsim(1:i+1);
    expref(i+1)=tmpexpref; expref=expref(1:i+1);
    idx(:,i+1)=tmpidx; idx=idx(:,1:i+1);
else
    netsim=tmpnetsim; dpsim=tmpdpsim; expref=tmpexpref; idx=tmpidx;
end;
if plt||details
    fprintf('\nNumber of exemplars identified: %d  (for %d data points)\n',K,N);
    fprintf('Net similarity: %g\n',tmpnetsim);
    fprintf('  Similarities of data points to exemplars: %g\n',dpsim(end));
    fprintf('  Preferences of selected exemplars: %g\n',tmpexpref);
    fprintf('Number of iterations: %d\n\n',i);
	fprintf('Elapsed time: %g sec\n',etime(clock,start));
end;
if unconverged
	fprintf('\n*** Warning: Algorithm did not converge. Activate plotting\n');
	fprintf('    so that you can monitor the net similarity. Consider\n');
	fprintf('    increasing maxits and convits, and, if oscillations occur\n');
	fprintf('    also increasing dampfact.\n\n');
end;
