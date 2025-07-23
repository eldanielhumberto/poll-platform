import useSWR, { SWRResponse } from 'swr';

const fetcher = async (url?: string | null, token?: string) => {
  if (url) {
    const headers: HeadersInit =
      token && token !== undefined ? { Authorization: token } : {};

    const r = await fetch(url as string, { headers });
    const d = await r.json();

    return d.data;
  }
};

export function useFetch<DataType>(path?: string | null, token?: string) {
  return useSWR(
    [path ? `${process.env.NEXT_PUBLIC_API_URL}${path}` : null, token],
    ([url, token]) => fetcher(url, token)
  ) as SWRResponse<DataType, null, null>;
}
