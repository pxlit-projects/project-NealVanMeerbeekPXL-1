package be.pxl.services.domain;

public enum ReviewStatus {
    NOT_YET_PERFORMED("Not yet performed"),
    PENDING("Pending"),
    REJECTED("Rejected"),
    APPROVED("Approved"),
    ;

    private final String name;

    ReviewStatus(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
