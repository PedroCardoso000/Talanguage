create table if not exists notification (
    id varchar(64) primary key,
    user_id varchar(64) not null references app_user(id) on delete cascade,
    title varchar(255) not null,
    message varchar(500) not null,
    action_route varchar(255),
    created_at timestamptz not null,
    read_at timestamptz
);

create index if not exists idx_notification_user_created_at
    on notification(user_id, created_at desc);
