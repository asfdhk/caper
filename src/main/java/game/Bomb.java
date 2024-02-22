package game;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs){
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    void start(){
        bombMap = new Matrix(Box.ZERO);
        for (int i= 0;i<totalBombs; i++)
            placeBomb();
    }

    Box get(Coord coord){
        return bombMap.get(coord);
    }

    private void fixBombCount(){
        int maxBomb = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBomb)
            totalBombs = maxBomb;

    }

    private void placeBomb(){
        while (true){
            Coord coord = Ranges.getRandomCoord();
            if(Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord,Box.BOMB);
            intNumbersAroundBomb(coord);
            break;
        }
    }

    private void intNumbersAroundBomb(Coord coord){
        for (Coord around: Ranges.getCoorrdsAround(coord)){
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around,bombMap.get(around).getNextNumberBox());
        }
    }

    int getTotalBombs(){
        return totalBombs;
    }


}
