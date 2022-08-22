package com.foodie.foodie.common.model;

public enum ResultCode {
    SUCCESS,                        // 성공
    INVALID_STATE,                  // 요청을 수행할 수 없는 상태
    PERMISSION_DENIED,              // 접근 권한 없음
    EXPIRED,                        // 시간 제약을 넘김
}

