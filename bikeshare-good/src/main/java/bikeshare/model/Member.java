
package bikeshare.model;

import bikeshare.MemberType;

public class Member {
    private final String id;
    private final MemberType type;

    public Member(String id, MemberType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public MemberType getType() {
        return type;
    }

    public double getRatePerMinute() {
        return type == MemberType.STUDENT ? 2.0 : 3.0;
    }
}
