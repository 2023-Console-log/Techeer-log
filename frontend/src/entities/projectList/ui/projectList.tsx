import { useGetProjectQuery } from '../query/useGetProjectQuery.tsx';
import { useInView } from 'react-intersection-observer';
import { useEffect, useMemo } from 'react';
import ProjectListCard from '../../../shared/ui/ProjectListCard.tsx';
import SkeletonCard from '../../../shared/ui/SkeletonCard.tsx';
import ProjectBoxCard from '../../../shared/ui/ProjectBoxCard.tsx';

type ProjectListProps = {
  selectedType: string;
  selectedYear: string;
  selectedPeriod: string;
  alignment: string | null;
  result: string;
};
/* prettier-ignore */
const filterOptions: Record<string, string> = {
  '팀 프로젝트': 'TEAM_PROJECT',
  '개인 프로젝트': 'PERSONAL_PROJECT',
  '부트캠프': 'BOOTCAMP',
  '동계': 'FIRST',
  '하계': 'SECOND',
};
//프로젝트 가져온 후 필터링
const useProjects = ({ selectedType, selectedYear, selectedPeriod, result }: ProjectListProps) => {
  const { data, hasNextPage, isFetching, isFetchingNextPage, fetchNextPage, isLoading } = useGetProjectQuery(result);
  const projects = data?.pages.flat() ?? [];
  const filteredProjects = useMemo(() => {
    let p = [...projects];
    // if (selectedType !== '프로젝트 종류' && selectedType !== '전체') {
    //   p = p.filter(({ projectTypeEnum }) => projectTypeEnum === filterOptions[selectedType]);
    // }
    if (selectedType == '부트캠프') {
      p = p.filter(({ projectTypeEnum }) => projectTypeEnum === filterOptions[selectedType]);
    } else {
      p = p.filter(
        ({ projectTypeEnum }) => projectTypeEnum === 'TEAM_PROJECT' || projectTypeEnum === 'PERSONAL_PROJECT',
      );
    }

    if (selectedYear !== '진행 연도' && selectedYear !== '전체') {
      p = p.filter(({ year }) => year === parseInt(selectedYear));
    }

    if (selectedPeriod !== '전체') {
      p = p.filter(({ semesterEnum }) => semesterEnum === filterOptions[selectedPeriod]);
    }
    return p;
  }, [projects, selectedType, selectedYear, selectedPeriod, filterOptions, result]);
  return {
    projects: filteredProjects,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    fetchNextPage,
    isLoading,
  };
};
export const ProjectList = ({ selectedType, selectedYear, selectedPeriod, alignment, result }: ProjectListProps) => {
  const { projects, hasNextPage, isFetching, isFetchingNextPage, fetchNextPage, isLoading } = useProjects({
    selectedType,
    selectedYear,
    selectedPeriod,
    alignment,
    result,
  });
  const { ref, inView } = useInView();
  useEffect(() => {
    if (inView && hasNextPage) {
      fetchNextPage();
    }
  }, [hasNextPage, fetchNextPage, inView]);
  if (isLoading && isFetching && !isFetchingNextPage) {
    return (
      <div className="grid grid-cols-3 grid-rows-3 gap-4 m-4">
        {[...Array(9)].map((_, index) => (
          <SkeletonCard key={index} />
        ))}
      </div>
    );
  }
  return (
    <>
      {alignment !== 'right' ? (
        <div className="grid grid-cols-3 grid-rows-3 gap-4 m-4">
          {projects && projects.length > 0 ? (
            projects.map((project) => <ProjectBoxCard key={project.id} project={project} />)
          ) : (
            <div className="flex flex-col -m-4 text-[1.25rem] px-[0.5rem] mb-[2rem] font-[400] text-[#90909a]">
              등록된 프로젝트가 없습니다.
            </div>
          )}
          {isFetchingNextPage ? <div className="w-full h-full bg-transparent">Loading...</div> : <div ref={ref} />}
        </div>
      ) : (
        <div className="m-4">
          {projects && projects.length > 0 ? (
            projects.map((project) => <ProjectListCard key={project.id} project={project} />)
          ) : (
            <div className="flex flex-col -m-4 text-[1.25rem] px-[0.5rem] mb-[2rem] font-[400] text-[#90909a]">
              등록된 프로젝트가 없습니다.
            </div>
          )}
          {isFetchingNextPage ? <div className="w-full h-full bg-transparent">Loading...</div> : <div ref={ref} />}
        </div>
      )}
    </>
  );
};
