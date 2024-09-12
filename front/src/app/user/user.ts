export interface User {
    id: number;
    username: string;
    password: string;
    enabled: boolean;
    firstName: string;
    lastName: string;
    email: string;
    role:string;
}
export const USER_LOCAL_STORAGE_KEY="currentUser"
