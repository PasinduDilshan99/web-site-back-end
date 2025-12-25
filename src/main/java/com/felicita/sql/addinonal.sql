-- get booking details with values
SELECT 
    'COMPLETED_TOURS' AS category,
    COUNT(*) AS count,
    SUM(b.final_amount) AS total_value
FROM bookings b
INNER JOIN booking_status bs ON b.booking_status_id = bs.id
WHERE b.user_id = 1 AND bs.name = 'TOUR_COMPLETED'
UNION ALL
SELECT 
    'UPCOMING_BOOKINGS' AS category,
    COUNT(*) AS count,
    SUM(b.final_amount) AS total_value
FROM bookings b
INNER JOIN booking_status bs ON b.booking_status_id = bs.id
WHERE b.user_id = 1 
AND bs.name IN ('CONFIRMED', 'PAID')
AND b.travel_start_date > CURDATE();