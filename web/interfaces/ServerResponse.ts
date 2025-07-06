export interface ServerResponse<Type> {
  message: string;
  data: Type;
  error?: string;
}
