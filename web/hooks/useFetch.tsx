import useSWR, { SWRResponse } from 'swr';

const fetcher = async (url: string, token: string | null) => {
  console.log(token);
  const headers: HeadersInit = token ? { Authorization: token } : {};
  const r = await fetch(url, { headers });
  console.log(r.headers);

  const d = await r.json();

  return d.data;
};

export function useFetch<DataType>(path: string, token: string | null) {
  return useSWR(
    [`${process.env.NEXT_PUBLIC_API_URL}${path}`, token],
    ([url, token]) => fetcher(url, token)
  ) as SWRResponse<DataType, null, null>;
}
