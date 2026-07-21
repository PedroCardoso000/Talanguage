alter table notification
    add column type varchar(32),
    add column deduplication_key varchar(255);

update notification
set type = 'LEGACY',
    deduplication_key = 'legacy:' || id;

alter table notification
    alter column type set not null,
    alter column deduplication_key set not null;

create unique index uq_notification_user_type_deduplication
    on notification(user_id, type, deduplication_key);
