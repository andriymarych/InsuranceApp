package com.marych.insuranceApp.service.diia;

public class DiiaCopy {

    private int diiaId;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String ITN;

    public DiiaCopy() {
    }

    public static Builder newBuilder() {
        return new DiiaCopy().new Builder();
    }

    public class Builder {
        public Builder() {
        }

        public Builder setDiiaId(int diiaId) {
            DiiaCopy.this.diiaId = diiaId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            DiiaCopy.this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            DiiaCopy.this.lastName = lastName;
            return this;
        }

        public Builder setBirthDate(String birthDate) {
            DiiaCopy.this.birthDate = birthDate;
            return this;
        }

        public Builder setITN(String ITN) {
            DiiaCopy.this.ITN = ITN;
            return this;
        }

        public DiiaCopy build() {
            return DiiaCopy.this;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getITN() {
        return ITN;
    }

    public int getDiiaId() {
        return diiaId;
    }
}
