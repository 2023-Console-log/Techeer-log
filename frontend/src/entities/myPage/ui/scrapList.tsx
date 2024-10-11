import { useQuery } from '@tanstack/react-query';
import { getScrapList } from '../api/getScrapList';
import ScrapCard from './scrapCard';
import { Scrap } from '../types/scrapList';
import { useState } from 'react';
import Pagination from '../../../shared/ui/Pagination';

export default function ScrapList() {
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 6; // 페이지당 보여줄 프로젝트 수

  // useQuery를 사용하여 스크랩 데이터를 가져옵니다.
  const { data, error, isLoading } = useQuery<Scrap[]>({
    queryKey: ['scrapList'],
    queryFn: getScrapList,
  });

  if (isLoading) {
    return <div className="w-full h-full bg-transparent">Loading...</div>;
  }

  if (error) {
    return <div>Error loading scrap list</div>;
  }

  // 현재 페이지에 해당하는 데이터를 계산합니다.
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentData = data?.slice(indexOfFirstItem, indexOfLastItem);

  // 총 페이지 수를 계산합니다.
  const totalPages = data ? Math.ceil(data.length / itemsPerPage) : 1;

  return (
    <div>
      <div className="grid grid-cols-3 grid-rows-2 gap-4 m-4">
        {currentData && currentData.length > 0 ? (
          currentData?.map((scrap) => <ScrapCard key={scrap.projectId} scrap={scrap} />)
        ) : (
          <div className="flex flex-col -m-4 text-[1.25rem] px-[0.5rem] mb-[2rem] font-[400] text-[#90909a]">
            등록된 프로젝트가 없습니다.
          </div>
        )}
      </div>
      <Pagination totalPages={totalPages} currentPage={currentPage} onPageChange={setCurrentPage} />
    </div>
  );
}
