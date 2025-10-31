import { LoginRequest, RegisterRequest, AuthResponse } from '@/types/auth';

const API_BASE = process.env.NEXT_PUBLIC_API_BASE_URL || 'http://localhost:8080';

export const authAPI = {
  login: async (credentials: LoginRequest): Promise<AuthResponse> => {
    const response = await fetch(`${API_BASE}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials),
    });

    if (!response.ok) {
      const message = await safeErrorMessage(response);
      throw new Error(message || 'Login failed');
    }

    return response.json();
  },

  register: async (userData: RegisterRequest): Promise<string> => {
    const response = await fetch(`${API_BASE}/auth/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      const message = await safeErrorMessage(response);
      throw new Error(message || 'Registration failed');
    }

    return response.text();
  },
};

async function safeErrorMessage(res: Response): Promise<string | undefined> {
  try {
    const ct = res.headers.get('content-type') || '';
    if (ct.includes('application/json')) {
      const body = await res.json();
      return body?.message || body?.error || body?.detail;
    }
    return await res.text();
  } catch {
    return undefined;
  }
}