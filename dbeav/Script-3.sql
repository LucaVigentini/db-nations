select c.name, r.region_id, r.name, c2.name 
from countries c
join regions r on c.region_id = r.region_id
join continents c2 on r.continent_id  = c2.continent_id
order by c.name asc;