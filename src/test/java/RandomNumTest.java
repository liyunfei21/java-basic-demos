import java.util.concurrent.ThreadLocalRandom;

public class RandomNumTest {
    /*
    * 对于生成随机数，推荐使用ThreadLocalRandom.current()产生，效率更高
    * 参考Effective Java
    *
    * */
    public static void main(String[] args) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for(int i = 0; 10 > i; i++){

            Object num = current.nextFloat();
            System.out.println(num);
        }
    }
}
