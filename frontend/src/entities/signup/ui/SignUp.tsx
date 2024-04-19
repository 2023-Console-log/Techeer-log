export default function SignUp() {
  return (
    //   확인용 배경 박스
    <div className="bg-[#111111] bg-opacity-90 flex w-screen h-screen justify-center items-center">
      <div className="rounded-[20px] bg-[url('./shared/assests/signup.svg')] bg-cover bg-no-repeat flex flex-row w-[960px] h-[600px] box-sizing-border">
        {/*왼쪽 박스*/}
        <div className="flex flex-col justify-center ml-8 box-sizing-border w-[55%] gap-2">
          <p className="break-words font-['Bayon'] font-normal text-[3rem] text-[#FFFFFF]">Techeer.log</p>
          <p className="inline-block self-start break-words font-['Pretendard'] font-semibold text-[1.4rem] text-[#FFFFFF]">
            환영합니다!
          </p>
          <p className="break-words font-['Pretendard'] font-light text-[1rem] text-[#FFFFFF]">
            테커에서 진행한 <span className="font-bold">당신의 프로젝트</span>를
            <span className="font-bold"> Techeer.log에 공유</span>해주세요!
          </p>
        </div>
        {/*오른쪽 박스*/}
        <div className="gap-4 backdrop-blur-[1.3rem] rounded-r-[20px] bg-[rgba(45,46,114,0.7)] flex flex-col justify-center items-center p-[0_2.5rem_0_2.5rem] w-[45%] box-sizing-border">
          <div className="mb-8 inline-block break-words font-['Pretendard'] font-semibold text-[2rem] text-[#F0F0F0]">
            회원가입
          </div>
          <div className="flex gap-4 sitems-center box-sizing-border">
            <div className="flex flex-col mt-2 mb-8 mr-2 items-start gap-[2.5rem]">
              <p className="text-[1rem] leading-7 font-semibold mb-30 text-[#ECEFF5]">이름</p>
              <p className="text-[1rem] leading-7 font-semibold mb-30 text-[#ECEFF5]">아이디</p>
              <p className="text-[1rem] leading-7 font-semibold mb-30 text-[#ECEFF5]">비밀번호</p>
              <p className="text-[1rem] leading-7 font-semibold mb-30 text-[#ECEFF5]">비밀번호 확인</p>
            </div>
            <div className="flex flex-col mb-8 items-start gap-[1.8rem]">
              <label className="block">
                <input
                  type="text"
                  name="nickname"
                  placeholder="이름을 입력하세요"
                  required
                  className="h-10 w-[14rem] text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"
                />
              </label>
              <label className="block">
                <input
                  type="text"
                  name="loginId"
                  placeholder="아이디를 입력하세요"
                  required
                  className="w-[14rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"
                />
              </label>
              <label className="block">
                <input
                  type="password"
                  name="password"
                  placeholder="비밀번호를 입력하세요"
                  required
                  className="w-[14rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"
                />
              </label>
              <label className="block">
                <input
                  type="password"
                  name="passwordconfirmation"
                  placeholder="비밀번호를 한번 더 입력하세요"
                  required
                  className="w-[14rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"
                />
              </label>
            </div>
            {/*<div className="flex mb-8 items-center gap-4">*/}
            {/*  <label className="block">*/}
            {/*    <input*/}
            {/*      type="text"*/}
            {/*      name="nickname"*/}
            {/*      placeholder="이름을 입력하세요"*/}
            {/*      required*/}
            {/*      className="w-[15rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"*/}
            {/*    />*/}
            {/*  </label>*/}
            {/*</div>*/}
            {/*<div className="flex mb-8 items-center gap-4">*/}
            {/*  <p className="text-[1.1rem] leading-7 pt-0 font-semibold mb-30 text-[#ECEFF5]">아이디</p>*/}
            {/*  <label className="block">*/}
            {/*    <input*/}
            {/*      type="text"*/}
            {/*      name="loginId"*/}
            {/*      placeholder="아이디를 입력하세요"*/}
            {/*      required*/}
            {/*      className="w-[15rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"*/}
            {/*    />*/}
            {/*  </label>*/}
            {/*</div>*/}
            {/*<div className="flex mb-8 items-center gap-4">*/}
            {/*  <p className="text-[1.1rem] leading-7 font-semibold mb-30 text-[#ECEFF5]">비밀번호</p>*/}
            {/*  <label className="block">*/}
            {/*    <input*/}
            {/*      type="password"*/}
            {/*      name="password"*/}
            {/*      placeholder="비밀번호를 입력하세요"*/}
            {/*      required*/}
            {/*      className="w-[15rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"*/}
            {/*    />*/}
            {/*  </label>*/}
            {/*</div>*/}
            {/*<div className="flex mb-8 items-center gap-4">*/}
            {/*  <p className="text-[1rem] leading-7 font-semibold mb-30 text-[#ECEFF5]">비밀번호 확인</p>*/}
            {/*  <label className="block">*/}
            {/*    <input*/}
            {/*      type="password"*/}
            {/*      name="passwordconfirmation"*/}
            {/*      placeholder="비밀번호를 한번 더 입력하세요"*/}
            {/*      required*/}
            {/*      className="w-[14rem] h-10 text-white bg-transparent border-b-2 border-gray-400 focus:border-white outline-none"*/}
            {/*    />*/}
            {/*  </label>*/}
            {/*</div>*/}
          </div>
          <div className="ml-4 rounded-[6px] bg-[#471993] flex flex-row justify-center w-[23rem] box-sizing-border">
            <span className="break-words font-['Pretendard'] font-normal text-[1rem] leading-[3] text-[#F0F0F0]">
              회원가입
            </span>
          </div>
        </div>
      </div>
    </div>
  );
}