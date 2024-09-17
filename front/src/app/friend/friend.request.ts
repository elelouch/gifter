import { User } from "../user/user";

export interface FriendRequest {
  requester: User;
  id: number;
  used: boolean;
}
