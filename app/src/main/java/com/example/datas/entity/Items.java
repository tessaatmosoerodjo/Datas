package com.example.datas.entity;

public class Items{
        private String title;
        private double price;
        private String size;
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        private int image;

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public Items(String title, double price, int image, String size, String color) {
            this.title = title;
            this.price = price;
            this.image = image;
            this.size = size;
            this.color = color;
        }
}
