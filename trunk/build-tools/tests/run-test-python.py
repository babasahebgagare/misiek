import os;
import sys;

test_params = []
test_params.append(["ToyProblemSimilarities.sim", "cen-ap1.out", "cen-ac1.out", "0.5", "10", "10", "-15", "cen-diff1.out", "centers"]);
test_params.append(["ToyProblemSimilarities.sim", "cen-ap2.out", "cen-ac2.out", "0.3", "10", "10", "-15", "cen-diff2.out", "centers"]);
test_params.append(["ToyProblemSimilarities.sim", "cen-ap3.out", "cen-ac3.out", "0.8", "10", "10", "-15", "cen-diff3.out", "centers"]);
test_params.append(["ToyProblemSimilarities.sim", "cen-ap4.out", "cen-ac4.out", "0.1", "100", "10", "-15", "cen-diff4.out", "centers"]);
test_params.append(["ToyProblemSimilarities.sim", "cen-ap5.out", "cen-ac5.out", "0.5", "100", "10", "-15", "cen-diff5.out", "centers"]);
test_params.append(["ToyProblemSimilarities.sim", "cen-ap6.out", "cen-ac6.out", "0.7", "100", "10", "-15", "cen-diff6.out", "centers"]);
test_params.append(["TravelRoutingSimilarities.sim", "cen-apt1.out", "cen-act1.out", "0.8", "10", "10", "-4500", "cen-difft1.out", "centers"]);
test_params.append(["TravelRoutingSimilarities.sim", "cen-apt2.out", "cen-act2.out", "0.8", "10", "10", "-4600", "cen-difft2.out", "centers"]);
test_params.append(["TravelRoutingSimilarities.sim", "cen-apt3.out", "cen-act3.out", "0.8", "10", "10", "-4700", "cen-difft3.out", "centers"]);
test_params.append(["TravelRoutingSimilarities.sim", "cen-apt4.out", "cen-act4.out", "0.8", "10", "10", "-4800", "cen-difft4.out", "centers"]);
#test_params.append(["TravelRoutingSimilarities.sim", "apt5.out", "act5.out", "0.8", "10", "10", "-000", "difft5.out", "centers"]);
#test_params.append(["TravelRoutingSimilarities.sim", "apt6.out", "act6.out", "0.8", "10", "1", "-2000", "difft6.out", "centers"]);



os.chdir("/home/students/inf/m/mw219725/studia/aff-comp");

oblicz = 0;

if(oblicz == 1):
    for test in test_params:
        command = 'echo "'+test[0]+' '+ test[1]+' '+ test[3]+' '+test[4]+' '+test[5]+' '+test[6]+' '+test[8]+'" > ~/studia/aff-comp/input';
        os.system(command);
        print command;
        os.system('/usr/local/bin/matlab < apclusterSparse.m');
        command2 = 'java -jar AffinityPropagationConsole.jar in='+test[0]+' out='+test[2]+' lam='+test[3]+' it='+test[4]+'  con='+test[5]+' p='+test[6]+' kind='+test[8]+' ref=true';
        os.system(command2);

compclust = 0;

if(compclust == 1):
    for test in test_params:
        command = 'diff ' +test[1]+' '+ test[2]+' > '+test[7];
        os.system(command);

        
comp = 1;

if(comp == 1):
    for test in test_params:
        fh1 = open(test[1]);
        fh2 = open(test[2]);
        lines1 = fh1.readlines();
        lines2 = fh2.readlines();
        good_l1 = [];
        for line in lines1:
            good_l1.append(line[0:len(line)]);
            good_l2 = [];
        for line in lines2:
            good_l2.append(line[0:len(line)]);
        s1 = set(good_l1);
        s2 = set(good_l2);
        if(s1.issubset(s2) and s2.issubset(s1)):
            fh = open(test[7],"w");
            fh.write("eq");
        else:
            fh = open(test[7],"w");
            fh.write("not eq");
            