import java.util.Arrays;

public class Solution {
    class File {
        private String name;
        private String head;
        private int number;

        public File(String name) {
            this.name = name;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    // files:  1000 개 이하
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        File[] fileInfos = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            fileInfos[i] = splitFileInfos(files[i]);
        }
        Arrays.sort(fileInfos, (o1, o2) -> {
            int returnValue = o1.head.compareTo(o2.head);
            if (returnValue == 0) {
                return o1.number - o2.number;
            }
            return returnValue;
        });
        for (int i = 0; i < files.length; i++) {
            answer[i] = fileInfos[i].name;
        }
        return answer;
    }

    private File splitFileInfos(String fileName) {
        StringBuilder sb = new StringBuilder();
        int len = fileName.length();
        File file = new File(fileName);
        fileName = fileName.toLowerCase();
        int i = 0;
        for (; i < len; i++) {
            if (Character.isDigit(fileName.charAt(i))) {
                break;
            }
            sb.append(fileName.charAt(i));
        }
        file.setHead(sb.toString());
        sb.setLength(0);
        // 한 글자에서 최대 다섯 글자 사이의 연속된 숫자, 0 이 앞에 올 수도 있음
        for (; i < len; i++) {
            if (sb.length() >= 5 || !Character.isDigit(fileName.charAt(i))) {
                break;
            }
            sb.append(fileName.charAt(i));
        }
        file.setNumber(Integer.parseInt(sb.toString()));
        sb.setLength(0);
        return file;
    }
}
