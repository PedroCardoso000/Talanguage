export type ApiErrorDetail = {
  field?: string;
  message: string;
};

export type ApiErrorResponse = {
  error: {
    code: string;
    message: string;
    details?: ApiErrorDetail[];
  };
};

export class ApiClientError extends Error {
  status?: number;
  details?: ApiErrorDetail[];

  constructor(message: string, options?: { status?: number; details?: ApiErrorDetail[] }) {
    super(message);
    this.name = "ApiClientError";
    this.status = options?.status;
    this.details = options?.details;
  }
}

type RequestOptions = {
  method?: "GET" | "POST" | "PUT" | "PATCH" | "DELETE";
  body?: unknown;
  headers?: HeadersInit;
  signal?: AbortSignal;
};

const DEFAULT_HEADERS = {
  "Content-Type": "application/json",
};

function normalizeBaseUrl(baseUrl?: string) {
  if (!baseUrl) {
    return "";
  }

  return baseUrl.endsWith("/") ? baseUrl.slice(0, -1) : baseUrl;
}

export class HttpClient {
  private readonly baseUrl: string;

  constructor(baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8080") {
    this.baseUrl = normalizeBaseUrl(baseUrl);
  }

  async request<TResponse>(path: string, options: RequestOptions = {}): Promise<TResponse> {
    const response = await fetch(`${this.baseUrl}${path}`, {
      method: options.method ?? "GET",
      headers: {
        ...DEFAULT_HEADERS,
        ...options.headers,
      },
      body: options.body ? JSON.stringify(options.body) : undefined,
      signal: options.signal,
    });

    if (!response.ok) {
      let payload: ApiErrorResponse | null = null;

      try {
        payload = (await response.json()) as ApiErrorResponse;
      } catch {
        payload = null;
      }

      throw new ApiClientError(
        payload?.error.message ?? `Request failed with status ${response.status}.`,
        {
          status: response.status,
          details: payload?.error.details,
        },
      );
    }

    if (response.status === 204) {
      return undefined as TResponse;
    }

    return (await response.json()) as TResponse;
  }

  get<TResponse>(path: string, options?: Omit<RequestOptions, "method" | "body">) {
    return this.request<TResponse>(path, { ...options, method: "GET" });
  }

  post<TResponse, TRequest = unknown>(
    path: string,
    body?: TRequest,
    options?: Omit<RequestOptions, "method" | "body">,
  ) {
    return this.request<TResponse>(path, { ...options, method: "POST", body });
  }

  put<TResponse, TRequest = unknown>(
    path: string,
    body?: TRequest,
    options?: Omit<RequestOptions, "method" | "body">,
  ) {
    return this.request<TResponse>(path, { ...options, method: "PUT", body });
  }

  patch<TResponse, TRequest = unknown>(
    path: string,
    body?: TRequest,
    options?: Omit<RequestOptions, "method" | "body">,
  ) {
    return this.request<TResponse>(path, { ...options, method: "PATCH", body });
  }

  delete<TResponse>(path: string, options?: Omit<RequestOptions, "method" | "body">) {
    return this.request<TResponse>(path, { ...options, method: "DELETE" });
  }
}

export function notImplementedClient(feature: string, operation: string): never {
  throw new ApiClientError(
    `${feature}.${operation} is not implemented yet. Backend integration is still pending.`,
  );
}

export const httpClient = new HttpClient();
