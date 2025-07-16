import useSWR, { SWRResponse } from 'swr';

const fetcher = async (url: string, token: string | null) => {
  const headers: HeadersInit = token ? { Authorization: token } : {};
  const r = await fetch(url, headers);
  const d = await r.json();

  return d.data;
};

export function useFetch<DataType>(path: string, token: string | null) {
  return useSWR(
    [`${process.env.NEXT_PUBLIC_API_URL}${path}`, token],
    ([url, token]) => fetcher(url, token)
  ) as SWRResponse<DataType, null, null>;
}
