import axiosInstance from '../../../shared/api/axiosInstance.ts';

export const getProject = (projectId: number) => {
  return axiosInstance.get(`/api/v1/projects/${projectId}`, {}).then((response) => response.data.data);
};

export const deleteProject = (projectId: number) => {
  return axiosInstance.delete(`/api/v1/projects/${projectId}`).then((response) => response.data.data);
};
