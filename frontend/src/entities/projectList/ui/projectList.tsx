import ProjectBoxCard from '../../../shared/ui/ProjectBoxCard.tsx';
import { useGetProjectQuery } from '../query/useGetProjectQuery.tsx';
import { useInView } from 'react-intersection-observer';
import { useEffect, useMemo } from 'react';
import ProjectListCard from '../../../shared/ui/ProjectListCard.tsx';
type ProjectListProps = {
  selectedType: string;
  selectedYear: string;
  selectedPeriod: string;
  alignment: string | null;
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
const useProjects = ({ selectedType, selectedYear, selectedPeriod }: ProjectListProps) => {
  const { data, hasNextPage, isFetching, isFetchingNextPage, fetchNextPage } = useGetProjectQuery();
  const projects = data?.pages.flat() ?? [];
  const filteredProjects = useMemo(() => {
    let p = [...projects];
    if (selectedType !== '프로젝트 종류' && selectedType !== '전체') {
      p = p.filter(({ projectTypeEnum }) => projectTypeEnum === filterOptions[selectedType]);
    }

    if (selectedYear !== '진행 연도' && selectedYear !== '전체') {
      p = p.filter(({ year }) => year === parseInt(selectedYear));
    }

    if (selectedPeriod !== '전체') {
      p = p.filter(({ semesterEnum }) => semesterEnum === filterOptions[selectedPeriod]);
    }
    return p;
  }, [projects, selectedType, selectedYear, selectedPeriod, filterOptions]);
  return {
    projects: filteredProjects,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    fetchNextPage,
  };
};
export const ProjectList = ({ selectedType, selectedYear, selectedPeriod, alignment }: ProjectListProps) => {
  const { projects, hasNextPage, isFetching, isFetchingNextPage, fetchNextPage } = useProjects({
    selectedType,
    selectedYear,
    selectedPeriod,
    alignment,
  });
  const { ref, inView } = useInView();
  useEffect(() => {
    if (inView && hasNextPage) {
      fetchNextPage();
    }
  }, [hasNextPage, fetchNextPage, inView]);
  if (isFetching && !isFetchingNextPage) {
    return <div className="w-full h-full bg-transparent">Loading...</div>;
  }
  return (
    <>
      {alignment !== 'right' ? (
        <div className="grid grid-cols-3 grid-rows-3 gap-4 m-4">
          {projects && projects.length > 0 ? (
            projects.map((project) => <ProjectBoxCard key={project.id} project={project} />)
          ) : (
            <div>No projects found.</div>
          )}
          {isFetchingNextPage ? <div className="w-full h-full bg-transparent">Loading...</div> : <div ref={ref} />}
        </div>
      ) : (
        <div className="m-4">
          {projects && projects.length > 0 ? (
            projects.map((project) => <ProjectListCard key={project.id} project={project} />)
          ) : (
            <div>No projects found.</div>
          )}
          {isFetchingNextPage ? <div className="w-full h-full bg-transparent">Loading...</div> : <div ref={ref} />}
        </div>
      )}
    </>
  );
};
