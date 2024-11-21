import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 1,          // 가상 유저 1명
    iterations: 10,  // 총 10번 반복
};
export default function () {
    const BASE_URL = "http://host.docker.internal:8080/api/v1"

    // 익명 토큰 발급
    let res = http.get(`${BASE_URL}/auth/anonymous`);
    let authToken = res.headers.Authorization;
    let headers = {
        Authorization: authToken, // 추출한 Authorization 값을 사용
    };

    let testRes = http.get(`${BASE_URL}/comments/1`, {headers:headers});
    check(testRes, {
        'comments status was 200': (r) => r.status === 200,
    });
    const parsedBody = JSON.parse(testRes.body);
    console.log(parsedBody.data.comments.length);

}
