class Solution {

    //    대기실은 5개이며, 각 대기실은 5x5 크기입니다.
    char[][] waitingRoom = new char[5][5];
    // 상하좌우
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    // 대각선
    int[] diagX = {-1, 1, 1, -1};
    int[] diagY = {1, 1, -1, -1};


    //각 대기실별로 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 배열에 담아 return
    //응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열 places
    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};
        //5*5 places 한 행 당 한 대기실
        // |r1 - r2| + |c1 - c2| 입니다.
        for (int i = 0; i < 5; i++) { // i번째 방
            for (int j = 0; j < 5; j++) { // j행
                boolean flag = true;
                for (int k = 0; k < 5; k++) { // k 열
                    waitingRoom[j][k] = places[i][j].charAt(k);
                    if (places[i][j].charAt(k) == 'P') {
                        if (!checkDistance(places[i], j, k)) { // 틀렸으면
                            answer[i] = 0;
                            flag = false;
                            break;
                        }
                    }
                }
                if (!flag) {
                    break;
                }
            }
        }
        return answer;
    }

    public boolean checkDistance(String[] places, int x, int y) {
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                continue;
            }
            if (places[nx].charAt(ny) == 'P') { // 상하좌우가 P 이면 거리두기 실패
                return false;
            } else if (places[nx].charAt(ny) == 'O') { // 빈 자리이고 다음 자리가 P 이면 거리두기 실패
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }
                if (places[nx].charAt(ny) == 'P') {
                    return false;
                }
            }
        }

        // 대각선 체크
        for (int i = 0; i < 4; i++) {
            nx = x + diagX[i];
            ny = y + diagY[i];
            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                continue;
            }
            if (places[nx].charAt(ny) == 'P') { // P 일 때 대각선이 벽으로 안나눠져있으면 거리두기 실패
                if (places[nx].charAt(y) != 'X' || places[x].charAt(ny) != 'X') {
                    return false;
                }
            }
        }

        return true;
    }
}