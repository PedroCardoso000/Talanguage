update learning_activity
set source_id = 'legacy:' || id
where source_id is null;

with duplicated_sources as (
    select id,
           row_number() over (
               partition by user_id, type, source_id
               order by completed_at, id
           ) as occurrence
    from learning_activity
)
update learning_activity activity
set source_id = 'legacy:' || activity.id
from duplicated_sources duplicate
where activity.id = duplicate.id
  and duplicate.occurrence > 1;

alter table learning_activity
    alter column source_id set not null;

create unique index if not exists uq_learning_activity_user_type_source
    on learning_activity(user_id, type, source_id);
