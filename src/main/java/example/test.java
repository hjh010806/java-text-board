package example;

public class test {
    public static void main(String[] args) {
        // 반복문 제어 -> break,
        // break -> 반복문 강제 종료
        // continue -> 다음 회차로 넘어감. 다음 반복문 처음으로 돌아감. continue 밑에있는 코드는 실행 되지않음.

        for(int i = 1; i <= 10; i++){
            if(i == 5){
                continue;
            }
            System.out.println(i);
        }
        int rst = test();
        System.out.println(rst);
    }

    public static int test(){
        int rst = 0;
        for (int i = 0; i < 10; i++) {
            if(i == 5){
                rst =1;
            }
        }
        return rst;
    }
}
