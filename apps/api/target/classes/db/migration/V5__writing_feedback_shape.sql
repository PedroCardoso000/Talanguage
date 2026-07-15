alter table writing_submission_metric
    add column if not exists strengths_json text not null default '[]';

alter table writing_submission_metric
    add column if not exists next_action text not null default 'Revise o texto e reescreva usando uma versao mais clara e completa.';
