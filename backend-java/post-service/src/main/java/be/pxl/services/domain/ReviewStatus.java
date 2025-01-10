package be.pxl.services.domain;

public enum ReviewStatus {
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
