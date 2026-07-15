export type NotificationResponse = {
  id: string;
  title: string;
  message: string;
  actionRoute: string | null;
  createdAt: string;
  readAt: string | null;
  read: boolean;
};
