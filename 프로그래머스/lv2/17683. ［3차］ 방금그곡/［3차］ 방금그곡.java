
public class Solution {
    public String solution(String m, String[] musicinfos) {
        String maxTitle = "(None)";
        int maxTime = -1;
        m = replaceMelody(m);
        // musicinfos : 시작 시각(HH:MM), 끝 시각, 제목, 악보정보
        for (String musicinfo : musicinfos) {
            String[] music = musicinfo.split(",");
            String[] start = music[0].split(":");
            String[] end = music[1].split(":");
            int startMinutes = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endMinutes = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            String melody = replaceMelody(music[3]); // 음 1개당 1분
            int totalMusicMinutes = melody.length(); // 음악 전체 시간
            int playMinutes = endMinutes - startMinutes;
            int leftMinutes = playMinutes;
            StringBuilder sb = new StringBuilder();
            while (leftMinutes > totalMusicMinutes) {
                sb.append(melody);
                leftMinutes -= totalMusicMinutes;
            }
            sb.append(melody, 0, leftMinutes);
            if (sb.toString().contains(m) && playMinutes > maxTime) {
                maxTitle = music[2];
                maxTime = playMinutes;
            }
        }
        
        return maxTitle;
    }

    String replaceMelody(String m){
        m = m.replace("A#","a");
        m = m.replace("C#","c");
        m = m.replace("D#","d");
        m = m.replace("F#","f");
        m = m.replace("G#","g");
        m = m.replace("E#","e");
        return m;
    }
}
