export default function ProjectCard(results: any) {
  console.log(results);
  return (
    <>
      {results.results &&
        results.results.map((res: any) => (
          <div
            className="rounded-[0.3rem] border-solid border border-[#444444] flex flex-col p-[0_0_1rem_0] box-sizing-border w-[100%]"
            key={res.id}
          >
            <div className="bg-[url('./shared/assets/image/cardImg/ThumbNailImg.png')] bg-cover rounded-[0.3rem] m-[0_0_1.3rem_0] w-[99.9%] h-[15rem]"></div>
            <div className="m-[0_1rem_0.4rem_1rem] inline-block self-start break-words font-['Pre-S'] font-semibold text-[1.3rem] bg-[#FFFFFF] text-[transparent] bg-clip-text">
              {res.title}
            </div>
            <div className="m-[0_1rem_0.7rem_1rem] inline-block self-start break-words font-['Pre-R'] font-normal text-[0.9rem] text-[#CCCCCC]">
              {res.subtitle}
            </div>
            <div className="rounded-[0.3rem] m-[0_1rem_2.2rem_1rem] flex flex-row self-start w-[fit-content] box-sizing-border">
              <div className="rounded-[0.3rem] bg-[#333333] relative m-[0_0.3rem_0_0] flex flex-row justify-center p-[0.3rem_0.7rem_0.2rem_0.6rem] box-sizing-border">
                <span className="break-words font-['Pre-R'] font-semibold text-[0.8rem] text-[#FFFFFF]">React</span>
              </div>
              <div className="rounded-[0.3rem] bg-[#333333] relative flex flex-row justify-center p-[0.3rem_0.7rem_0.2rem_0.7rem] box-sizing-border">
                <span className="break-words font-['Pre-R'] font-semibold text-[0.8rem] text-[#FFFFFF]">Spring</span>
              </div>
            </div>
            <div className="m-[0_1rem_0_1rem] flex flex-row justify-between w-[92%] box-sizing-border">
              <p className="m-[0_0.5rem_0_0] break-words font-['Pre-R'] font-normal text-[0.8rem] text-[#B0B0B0]">
                <span className="container-17-sub-10"></span>
                {res.projectStatus === 'RUNNING' ? (
                  <>
                    <span className="text-green-400">•</span>
                    <span> 서비스 운영중</span>
                  </>
                ) : (
                  <span>• 서비스 운영중 아님</span>
                )}
              </p>
              <span className="break-words font-['Pre-R'] font-normal text-[0.8rem] text-[#B0B0B0]">
                ♥&nbsp;&nbsp;&nbsp;
                {res.loveCount}
              </span>
            </div>
          </div>
        ))}
    </>
  );
}
