package com.thorton.grant.uspto.prototypewebapp.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "user_personal_data")
public class UserPersonalData extends BaseEntity {


        @Column(name = "first_name")

        private String firstName;

        @Column(name = "last_name")

        private String lastName;

        @Column(name = "midlle_name")

        private String midlleName;

        @Column(name = "title")
        private String title;

        @Column(name = "suffix")
        private String suffix;


        @Column(name = "address")
        private String address;

        @Column(name="city")
        private String city;

        @Column(name="phoneNumber")
        private String[] phoneNumber;



        @Column(name="email")
        private String email;

        @Column(name="alternate_email")
        private String alertnateEmail;



        // add image object for user profile picture

        @OneToOne(cascade = CascadeType.ALL)
        private UserCredentials userCredentials ;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }



        public UserCredentials getUserCredentials() {
            return userCredentials;
        }

        public void setUserCredentials(UserCredentials userCredentials) {
            this.userCredentials = userCredentials;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public String getMidlleName() {
            return midlleName;
        }

        public void setMidlleName(String midlleName) {
            this.midlleName = midlleName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }


        public String[] getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String[] phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAlertnateEmail() {
            return alertnateEmail;
        }

        public void setAlertnateEmail(String alertnateEmail) {
            this.alertnateEmail = alertnateEmail;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserPersonalData that = (UserPersonalData) o;
            return Objects.equals(firstName, that.firstName) &&
                    Objects.equals(lastName, that.lastName) &&
                    Objects.equals(midlleName, that.midlleName) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(suffix, that.suffix) &&
                    Objects.equals(address, that.address) &&
                    Objects.equals(city, that.city) &&
                    Arrays.equals(phoneNumber, that.phoneNumber) &&
                    Objects.equals(email, that.email) &&
                    Objects.equals(alertnateEmail, that.alertnateEmail) &&
                    Objects.equals(userCredentials, that.userCredentials);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(firstName, lastName, midlleName, title, suffix, address, city, email, alertnateEmail, userCredentials);
            result = 31 * result + Arrays.hashCode(phoneNumber);
            return result;
        }

        @Override
        public String toString() {
            return "UserPersonalData{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", midlleName='" + midlleName + '\'' +
                    ", title='" + title + '\'' +
                    ", suffix='" + suffix + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", phoneNumber=" + Arrays.toString(phoneNumber) +
                    ", email='" + email + '\'' +
                    ", alertnateEmail='" + alertnateEmail + '\'' +
                    ", userCredentials=" + userCredentials +
                    '}';
        }
}
