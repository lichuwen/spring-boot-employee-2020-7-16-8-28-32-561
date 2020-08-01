alter table company_employees drop constraint if exists UK_lg2r1rg13q18sa62l1y7un4or;
alter table company_employees add constraint UK_lg2r1rg13q18sa62l1y7un4or unique (employees_id);
alter table company_employees add constraint FKnntnqhhla66c4h9ddbnlvqk2x foreign key (employees_id) references employee;
alter table company_employees add constraint FKf5m7np52tslodab595l720wbw foreign key (company_companyid) references company;