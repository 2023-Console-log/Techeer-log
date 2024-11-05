import arrow from '../image/arrow.png';
import object from '../image/object.png';
import iconPoint from '../../../shared/assets/image/mainImg/Icon-Point.png';
//import { EmblaCarousel } from '../../../entities/carousel';
import React, { Suspense, useEffect, useRef } from 'react';
import { EmblaOptionsType } from 'embla-carousel';
import { prizeDate } from '../../../shared/types/prizeDate';
import useScrollCount from '../hook/useScrollHook';
// import useScrollFadeIn from '../hook/useScrollFadIn';
import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';
const EmblaCarousel = React.lazy(() =>
  import('../../../entities/carousel/ui/Carousel.tsx').then((module) => ({
    default: module.EmblaCarousel,
  })),
);

export default function Bootcamp() {
  const OPTIONS: EmblaOptionsType = { loop: true };
  const queryParams = new URLSearchParams(location.search);
  const searchQuery = queryParams.get('search') || '';

  const animatedItem1 = useScrollCount(8, 0, 1000);
  const animatedItem2 = useScrollCount(600, 0, 1000);
  const animatedItem3 = useScrollCount(100, 0, 3000);

  // const animatedItem = {
  //   0: useScrollFadeIn('up', 1, 0),
  //   1: useScrollFadeIn('up', 1, 0.2),
  //   2: useScrollFadeIn('up', 1, 0.3),
  // };

  const scrollRef = useRef<HTMLDivElement | null>(null);
  useEffect(() => {
    if (searchQuery) {
      scrollRef.current?.scrollIntoView({ behavior: 'smooth' });
    }
  }, [searchQuery]);

  const data: prizeDate = {
    projectTypeEnum: 'BOOTCAMP',
    year: 2024,
    semesterEnum: 'SECOND',
  };
  function renameSemester(semester: string) {
    if (semester === 'FIRST') return '동계';
    if (semester === 'SECOND') return '하계';
    else return '';
  }

  return (
    <div className="w-[100vw] h-[70vw] flex flex-col my-20 items-center text-white">
      <div className="flex gap-[2rem] my-[2rem]">
        <div className="w-[33rem] border-t-[0.1rem] border-t-white border-solid"></div>
        <span className="font-['Pre-S']">Since 2020</span>
        <div className="w-[33rem] border-t-[0.1rem] border-t-white border-solid"></div>
      </div>
      {/* 카운트 */}
      <div className="flex gap-[15rem]">
        <div className="flex flex-col items-center w-[6rem]">
          <span className="font-['Pre-S'] text-[3.5rem]" {...animatedItem1}>
            8기
          </span>
          <span className="font-['Pretendard-Medium'] text-[1.1rem]">부트캠프 기수</span>
        </div>
        <div className="flex flex-col items-center">
          <span className="font-['Pre-S'] text-[3.5rem]" {...animatedItem2}>
            600명 +
          </span>
          <span className="font-['Pretendard-Medium'] text-[1.1rem]">누적 수료생</span>
        </div>
        <div className="flex flex-col items-center">
          <span className="font-['Pre-S'] text-[3.5rem]" {...animatedItem3}>
            100개 +
          </span>
          <span className="font-['Pretendard-Medium'] text-[1.1rem]">진행한 프로젝트</span>
        </div>
      </div>
      {/* 텍스트 */}
      <div className="w-[100vw] h-[35vw] my-[10rem] flex flex-col bg-cover bg-[url('./entities/onboarding/image/bg.png')]">
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: false }}
          transition={{
            ease: 'easeInOut',
            duration: 1,
          }}
        >
          <div className="flex justify-center pt-[4rem] pb-[1rem]">
            <div
              className="font-['Pre-S'] text-[3rem] bg-clip-text"
              style={{
                backgroundImage: 'linear-gradient(to bottom,#E4EDFF, #0047FF)',
                WebkitBackgroundClip: 'text',
                WebkitTextFillColor: 'transparent',
              }}
            >
              부트캠프&nbsp;
            </div>
            <div className="font-['Pre-S'] text-[3rem] text-white"> 결과물 한눈에 보기</div>
          </div>
        </motion.div>
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: false }}
          transition={{
            ease: 'easeInOut',
            duration: 1,
          }}
        >
          <div className="flex justify-center mb-[2rem] font-['Pretendard-Medium'] text-[1.1rem]">
            매년 2회 2번의 6주간 진행되는 실리콘밸리 실무 기반 SW 부트캠프
          </div>
        </motion.div>
        <motion.div
          className="mx-auto rounded-[3rem] border-solid border-[1px] border-black shadow-[0_35px_60px_-15px_rgba(146, 146, 146, 0.3)]"
          style={{
            boxShadow: '0px 4px 4px -2px rgba(146, 146, 146, 0.49)',
          }}
          initial={{ opacity: 0, y: 30 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: false }}
          transition={{
            ease: 'easeInOut',
            duration: 1,
            delay: 0.5,
          }}
        >
          <Link to="/project">
            <div className="flex w-[11rem] h-[3rem] justify-center items-center gap-[1rem]">
              <span className="font-['Pretendard-Medium'] text-[1.3rem] text-white">보러가기</span>
              <img className="w-[2rem] h-[2rem] flex" src={arrow} alt="버튼" />
            </div>
          </Link>
        </motion.div>
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: false }}
          transition={{
            ease: 'easeInOut',
            duration: 1,
            delay: 0.4,
          }}
        >
          <img className="w-[30rem] h-[30rem] flex mx-auto" src={object} alt="" />
        </motion.div>
      </div>
      {/* 우수 선정작 */}
      <div className="w-[75rem] mt-[6.063rem] flex flex-col justify-center mb-[15rem]">
        <div className="flex flex-col items-center justify-center my-12">
          <img src={iconPoint} className="w-[1.875rem] h-[0.75rem] mb-[1rem]" />
          <span className="font-['Pretendard-Thin'] text-[1.875rem] text-white">
            {data.year} {renameSemester(data.semesterEnum)} 부트캠프
            <a className="font-['Pretendard-Bold']"> 우수 선정작</a>
          </span>
        </div>
        <div className="overflow-x-hidden w-[98%] mx-auto mb-[6.25rem]">
          <Suspense fallback={<div>Loading...</div>}>
            <EmblaCarousel options={OPTIONS} date={data} />
          </Suspense>
          <div ref={scrollRef}></div>
        </div>
      </div>
    </div>
  );
}
