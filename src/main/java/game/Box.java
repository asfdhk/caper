package game;

public enum Box {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;

    Box getNextNumberBox(){
        return Box.values()[this.ordinal() + 1];//берем наступне число
    }

}
