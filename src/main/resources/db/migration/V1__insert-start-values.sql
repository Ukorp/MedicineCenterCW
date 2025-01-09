insert into service (id, price, name)
VALUES (1, 1000, 'Сбор крови'),
       (2, 4000, 'КТ'),
       (3, 6000, 'МРТ'),
       (4, 700, 'Приём стоматолога'),
       (5, 1000, 'Приём венеролога'),
       (6, 1000, 'Приём дерматолога'),
       (7, 1000, 'Приём офтальмолога'),
       (8, 1000, 'Приём терапевта');

insert into branches (id, name, address)
values (1, 'Филиал 1', 'Москва, Факультетский пер. д.12'),
       (2, 'Филиал 2', 'Москва, кл Дубосековская д.5с1');

insert into doctors (id, name, branch_id)
values (1, 'Михаил Петрович Малышев', 1),
       (2, 'Богдан Виссарионович Сталин', 1),
       (3, 'Михаил Петрович Малышев', 2),
       (4, 'Мистер Бист', 2);

insert into services_doctors (doctor_id, service_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);

create or replace function log_action() returns trigger as
$log_act$
begin
    insert into logs (action, table_name, change_time, details)
    select TG_OP,
           tg_table_name,
           now(),
           case
               when TG_OP ILIKE 'INSERT' then 'Added' || NEW::TEXT
               when TG_OP ILIKE 'UPDATE' then 'Updated' || NEW::TEXT
               when TG_OP ILIKE 'DELETE' then 'Deleted' || OLD::TEXT
               end;
    return new;
end ;
$log_act$ LANGUAGE plpgsql;

create or replace trigger log_act_booking
    AFTER INSERT OR UPDATE OR DELETE on booking
    for each row execute procedure log_action();

create or replace trigger log_act_doctors
    AFTER INSERT OR UPDATE OR DELETE on doctors
    for each row execute procedure log_action();

create or replace trigger log_act_service
    AFTER INSERT OR UPDATE OR DELETE on service
    for each row execute procedure log_action();

create or replace trigger log_act_services_doctors
    AFTER INSERT OR UPDATE OR DELETE on services_doctors
    for each row execute procedure log_action();

create or replace trigger log_act_user_data
    AFTER INSERT OR UPDATE OR DELETE on user_data
    for each row execute procedure log_action();

SET time zone 'Europe/Moscow';
