import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class LineAndPointCollision extends JFrame implements KeyListener{
    char[][] pixels = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};
    int[] pointsRows = {1, 1, 3};
    int[] pointsCols = {0, 15, 7};
    boolean gameRun = true;
    boolean wKey = false;
    boolean aKey = false;
    boolean sKey = false;
    boolean dKey = false;
    boolean upKey = false;
    boolean leftKey = false;
    boolean downKey = false;
    boolean rightKey = false;
    boolean colliding = false;

    public LineAndPointCollision() {
        this.setTitle("f");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
	    gameLoop();	
    }
	
    private void gameLoop(){
	while (gameRun == true){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int a = 0; a < 6; a++){
            for (int b = 0; b < 16; b++){
                pixels[a][b] = ' ';
            }
        }
        
        if (wKey == true && pointsRows[0] > 0){
            pointsRows[0] = pointsRows[0] - 1;
        }
        if (aKey == true && pointsCols[0] > 0){
            pointsCols[0] = pointsCols[0] - 1;
        }
        if (sKey == true && pointsRows[0] < 5){
            pointsRows[0] = pointsRows[0] + 1;
        }
        if (dKey == true && pointsCols[0] + 4 < 15){
            pointsCols[0] = pointsCols[0] + 1;
        }

        if (leftKey == true && pointsCols[1] > 0){
            pointsCols[1] = pointsCols[1] - 1;
        }
        if (upKey == true && pointsRows[1] > 0){
            pointsRows[1] = pointsRows[1] - 1;
        }
        if (downKey == true && pointsRows[1] + 4 < 5){
            pointsRows[1] = pointsRows[1] + 1;
        }
        if (rightKey == true && pointsCols[1] < 15){
            pointsCols[1] = pointsCols[1] + 1;
        }
        
        //draw horizontal line
        for (int r = pointsCols[0]; r < pointsCols[0] + 4; r++){
            if (r <= 15){
                pixels[pointsRows[0]][r] = '#';
            }
        }

        //draw vertical line
        for (int s = pointsRows[1]; s < pointsRows[1] + 4; s++){
            if (s <= 5){
                pixels[s][pointsCols[1]] = '#';
            }
        }

        //draw point
        pixels[pointsRows[2]][pointsCols[2]] = '*';

        //collision
        if ((pointsRows[0] == pointsRows[2] && pointsCols[2] >= pointsCols[0] && pointsCols[2] < pointsCols[0] + 4) || (pointsCols[1] == pointsCols[2] && pointsRows[2] >= pointsRows[1] && pointsRows[2] < pointsRows[1] + 4) || (pointsRows[0] >= pointsRows[1] && pointsRows[0] < pointsRows[1] + 4 && pointsCols[1] >= pointsCols[0] && pointsCols[1] < pointsCols[0] + 4)){
            colliding = true;
        }
        else{
            colliding = false;
        }
        
        //draw everything
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 16; j++){
                System.out.print(pixels[i][j]);
            }
            System.out.println();
        }
        System.out.println("Colliding: " + colliding);
        
	    try {
		Thread.sleep(100);
            } catch (InterruptedException e) {
		e.printStackTrace();
	    }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this program
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //horizontal line input
        if (e.getKeyChar() == 'w'){
            wKey = true;
        }
        if (e.getKeyChar() == 'a'){
            aKey = true;
        }
        if (e.getKeyChar() == 's'){
            sKey = true;
        }
        if (e.getKeyChar() == 'd'){
            dKey = true;
        }
        
        if (e.getKeyCode() == 37){
            leftKey = true;
        }
        if (e.getKeyCode() == 38){
            upKey = true;
        }
        if (e.getKeyCode() == 39){
            rightKey = true;
        }
        if (e.getKeyCode() == 40){
            downKey = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //horizontal line input
        if (e.getKeyChar() == 'w'){
            wKey = false;
        }
        if (e.getKeyChar() == 'a'){
            aKey = false;
        }
        if (e.getKeyChar() == 's'){
            sKey = false;
        }
        if (e.getKeyChar() == 'd'){
            dKey = false;
        }

        if (e.getKeyCode() == 37){
            leftKey = false;
        }
        if (e.getKeyCode() == 38){
            upKey = false;
        }
        if (e.getKeyCode() == 39){
            rightKey = false;
        }
        if (e.getKeyCode() == 40){
            downKey = false;
        }

    }
    
    public static void main(String[] args) {
       new LineAndPointCollision();
    }
}