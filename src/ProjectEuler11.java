import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ebayram on 1.10.2016.
 */
public class ProjectEuler11 {


    public void run() throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

        int grid[][]=new int[20][20];
        int maxMult=0;
        for (int i = 0; i < 20; i++) {
            String []line=bi.readLine().split(" ");
            for(int j=0;j<20;j++){
                grid[i][j]=Integer.parseInt(line[j]);



            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(j+3<20){
                    int toRight=grid[i][j]*grid[i][j+1]*grid[i][j+2]*grid[i][j+3];
                    if(toRight>maxMult){
                        maxMult=toRight;
                    }
                }
                if(i+3<20){
                    int toDown=grid[i][j]*grid[i+1][j]*grid[i+2][j]*grid[i+3][j];
                    if(toDown>maxMult){
                        maxMult=toDown;
                    }
                    if(j+3<20){
                        int toDiag=grid[i][j]*grid[i+1][j+1]*grid[i+2][j+2]*grid[i+3][j+3];
                        if(toDiag>maxMult){
                            maxMult=toDiag;
                        }
                    }
                    if(j-3>=0){
                        int toDiag=grid[i][j]*grid[i+1][j-1]*grid[i+2][j-2]*grid[i+3][j-3];
                        if(toDiag>maxMult){
                            maxMult=toDiag;
                        }
                    }
                }
            }
        }

        System.out.println(maxMult);
    }


    void main() throws IOException {
        run();
    }

    public static void main(String[] args) throws IOException {
        new ProjectEuler11().main();
    }
}
