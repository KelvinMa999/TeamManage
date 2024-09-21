package project01.team.service;

import project01.team.domain.Architect;
import project01.team.domain.Designer;
import project01.team.domain.Employee;
import project01.team.domain.Programmer;

public class TeamService {
    private static int counter = 1;
    private final int MAX_MEMBER = 5;

    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;

    public TeamService(){

    }
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];

        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }
    public void addMember(Employee e) throws TeamException {
        if (total >= MAX_MEMBER)
            throw new TeamException("full，can't add");
        if (!(e instanceof Programmer))
            throw new TeamException("not devloper，can't add");

        Programmer p = (Programmer)e;
        switch (p.getStatus()) {
            case BUSY    :throw new TeamException("this member already in a team");
            case VOCATION:throw new TeamException("OFF duty，can't add");
        }

        if (isExist(p))
            throw new TeamException("already in this team");

        int numOfArch = 0, numOfDsgn = 0, numOfPrg = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) numOfArch++;
            else if (team[i] instanceof Designer) numOfDsgn++;
            else if (team[i] instanceof Programmer) numOfPrg++;
        }

        if (p instanceof Architect) {
            if (numOfArch >= 1) throw new TeamException("Only one Architech in a team");
        } else if (p instanceof Designer) {
            if (numOfDsgn >= 2) throw new TeamException("Only two Designers in a team");
        } else if (p instanceof Programmer) {
            if (numOfPrg >= 3) throw new TeamException("Only three programmers in a team");
        }
        
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;
    }

    private boolean isExist(Programmer p) {
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == p.getId()) return true;
        }

        return false;
    }
    public void removeMember(int memberId) throws TeamException {
        int n = 0;
        for (; n < total; n++) {
            if (team[n].getMemberId() == memberId) {
                team[n].setStatus(Status.FREE);
                break;
            }
        }

        if (n == total)
            throw new TeamException("can't find this employee，can't delete");
        
        for (int i = n + 1; i < total; i++) {
            team[i - 1] = team[i];
        }
        team[--total] = null;
    }

}
