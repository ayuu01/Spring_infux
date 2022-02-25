package com.example.Spring_infux.entity;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;

    @Data
    @Measurement(name = "temperature")
    public class Temperature {

        @Column(name = "time")
        private Instant time;

        @Column(name = "location")
        public String location;

        @Column(name = "value")
        private String value;

        public Instant getTime() {
            return time;
        }

        public void setTime(Instant time) {
            this.time = time;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
