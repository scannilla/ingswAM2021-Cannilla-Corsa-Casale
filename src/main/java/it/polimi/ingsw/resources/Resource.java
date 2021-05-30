package it.polimi.ingsw.resources;

import java.util.Objects;

public class Resource {
    /**
     * This attribute represents type of this Resource
     * coin 0
     * stone 1
     * servant 2
     * shield 3
     */
    private final int resType;
    /**
     * This method creates a Resource with this type
     * @param resType int
     */
    public Resource(int resType) {
        this.resType = resType;
    }
    /**
     * This method returns type of this Resource
     * @return int type
     */
    public int getResType() {
        return resType;
    }

    public Resource(String string) {
        switch (string) {
            case "coin":
                this.resType=0;
                break;
            case "stone":
                this.resType=1;
                break;
            case "servant":
                this.resType=2;
                break;
            case "shield":
                this.resType=3;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return resType == resource.resType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resType);
    }

    /**
     * This method returns this Resource formatted into a string
     * @return String
     */
    @Override
    public String toString() {
        switch(resType) {
            case 0: return "coin";
            case 1: return "stone";
            case 2: return "servant";
            case 3: return "shield";
        }
        return null;
    }
}
