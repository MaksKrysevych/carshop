package carshop.model.enums;

public enum Statuses {
    CREATED, AVAILIBLE, PAID;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
