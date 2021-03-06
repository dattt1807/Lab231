create database UserManagement;
use UserManagement;
create table tblRoles(
	roleID varchar(10) primary key,
	roleName varchar(50)
);
create table tblUsers(
	userID varchar(50) primary key,
	firstName varchar(50),
	lastName varchar(50),
	password varchar(max),
	isSendNotification bit not null,
	email varchar(50),
	roleID varchar(10) not null,
	foreign key (roleID) references tblRoles(roleID)
);

insert into tblRoles values ('AD', 'Adminitrator');
insert into tblRoles values ('SU', 'Subscriber');
insert into tblRoles values ('TE', 'Test');

insert into tblUsers values ('emvh', 'Hoai Em', 'Vo', '123', 0, 'emvh@gmail.com', 'SU');
insert into tblUsers values ('sa', 'Admin', '', '123', 0, 'admin@gmail.com', 'AD');

select r.roleName from tblUsers u, tblRoles r where u.userID = 'sa' and u.password = '123' and u.roleID = r.roleID;\
select u.userID, u.firstName, u.lastName, u.email, r.roleName
from tblUsers u, tblRoles r
where u.roleID = r.roleID
select * from tblUsers;
select roleID, roleName from tblRoles;

update tblUsers set roleID = 'SU' where userID in ('emvh','chau')
update tblUsers set roleID = 'AD' where userID in ('sa')
select * from tblUsers

insert into tblUsers(userID, firstName, lastName, password, email, roleID, isSendNotification)
values('hoaiem', 'Hoai Em', 'Vo', '1', 'hoaiem@gmail.com', 'SU', '1');