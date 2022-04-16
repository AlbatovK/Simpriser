package com.albatros.simspriser.session;

class SendData {

    private long id;
    private int position;
    private boolean right;
    private int time;

    public SendData(long id, int position, boolean right, int time) {
        this.id = id;
        this.position = position;
        this.right = right;
        this.time = time;
    }

    public SendData() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}