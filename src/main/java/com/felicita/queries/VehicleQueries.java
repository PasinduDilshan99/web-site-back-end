package com.felicita.queries;

public class VehicleQueries {

    public static final String GET_VEHICLES_DETAILS = """
            SELECT
                v.vehicle_id,
                v.registration_number,
                v.specification_id,
                v.status_id,
                cs.name AS status_name,
                v.purchase_date,
                v.purchase_price,
                v.created_at AS vehicle_created_at,
                v.created_by AS vehicle_created_by,
                v.updated_at AS vehicle_updated_at,
                v.updated_by AS vehicle_updated_by,
                v.terminated_at AS vehicle_terminated_at,
                v.terminated_by AS vehicle_terminated_by,
            	vs.make,
                vs.model,
                vs.year,
                vs.generation,
                vs.body_type,
                vs.price AS specification_price,
                vs.engine_type,
                vs.engine_capacity,
                vs.horsepower_hp,
                vs.torque_nm,
                vs.transmission_type_id,
                vs.fuel_type_id,
                vs.electric_range_km,
                vs.drivetrain,
                vs.top_speed_kmh,
                vs.acceleration_0_100,
                vs.co2_emissions_g_km,
                vs.doors,
                vs.seat_capacity,
                vs.dimensions,
                vs.wheelbase_mm,
                vs.weight_kg,
                vs.wheel_size,
                vs.tire_type,
                vs.upholstery_type,
                vs.ac_type_id,
                vs.sunroof_type,
                vs.cruise_control_type,
                vs.entertainment_features,
                vs.comfort_features,
                vs.ncap_safety_rating,
                vs.airbags_count,
                vs.parking_camera,
                vs.lane_departure_warning,
                vs.safety_features,
                vs.fuel_tank_capacity_liters,
                vs.warranty_years,
                vs.image_url AS specification_image_url,
                vs.is_active AS specification_active,
                vs.created_at AS specification_created_at,
                vs.created_by AS specification_created_by,
                vs.updated_at AS specification_updated_at,
                vs.updated_by AS specification_updated_by,
                vs.terminated_at AS specification_terminated_at,
                vs.terminated_by AS specification_terminated_by,
            	vi.image_id AS vehicle_image_id,
                vi.image_url AS vehicle_image_url,
                vi.image_name AS vehicle_image_name,
                vi.description AS vehicle_image_description,
                vi.created_at AS image_created_at,
                vi.created_by AS image_created_by,
                vi.updated_at AS image_updated_at,
                vi.updated_by AS image_updated_by,
                vi.terminated_at AS image_terminated_at,
                vi.terminated_by AS image_terminated_by,
                vul.usage_id,
                vul.package_id,
                vul.tour_id,
                vul.start_datetime,
                vul.end_datetime,
                vul.start_odometer,
                vul.end_odometer,
                vul.route_description,
                vul.purpose AS usage_purpose,
                vul.fuel_used_liters,
                vul.remarks AS usage_remarks,
                vul.created_at AS usage_created_at,
                vul.created_by AS usage_created_by,
                vul.updated_at AS usage_updated_at,
                vul.updated_by AS usage_updated_by,
                vul.terminated_at AS usage_terminated_at,
                vul.terminated_by AS usage_terminated_by
            FROM vehicles v
            LEFT JOIN common_status cs
                ON v.status_id = cs.id
            LEFT JOIN vehicle_specifications vs
                ON v.specification_id = vs.specification_id
            LEFT JOIN vehicle_images vi
                ON v.vehicle_id = vi.vehicle_id
            LEFT JOIN vehicle_usage_log vul
                ON v.vehicle_id = vul.vehicle_id
            ORDER BY v.vehicle_id, vul.start_datetime DESC
            """;


    public static final String GET_VEHICLES_DETAILS_BY_ID = """
            SELECT
                v.vehicle_id,
                v.registration_number,
                v.status_id,
                cs.name AS status_name,
                v.purchase_date AS vehicle_purchase_date,
                v.purchase_price AS vehicle_purchase_price,
                v.created_at AS vehicle_created_at,
                v.created_by AS vehicle_created_by,
                v.updated_at AS vehicle_updated_at,
                v.updated_by AS vehicle_updated_by,
                v.terminated_at AS vehicle_terminated_at,
                v.terminated_by AS vehicle_terminated_by,
                v.owner_id,
                v.assigned_driver_id,
                vs.specification_id,
                vs.make,
                vs.model,
                vs.year AS vehicle_year,
                vs.generation,
                vs.body_type,
                vs.price AS specification_price,
                vs.engine_type,
                vs.engine_capacity,
                vs.horsepower_hp,
                vs.torque_nm,
                vs.transmission_type_id,
                tt.transmission_type_name,
                vs.fuel_type_id,
                ft.fuel_type_name,
                vs.electric_range_km,
                vs.drivetrain,
                vs.top_speed_kmh,
                vs.acceleration_0_100,
                vs.co2_emissions_g_km,
                vs.doors,
                vs.seat_capacity,
                vs.dimensions,
                vs.wheelbase_mm,
                vs.weight_kg,
                vs.wheel_size,
                vs.tire_type,
                vs.upholstery_type,
                vs.ac_type_id,
                ac.ac_type_name AS ac_type,
                vs.sunroof_type,
                vs.cruise_control_type,
                vs.entertainment_features,
                vs.comfort_features,
                vs.ncap_safety_rating,
                vs.airbags_count,
                vs.parking_camera,
                vs.lane_departure_warning,
                vs.safety_features,
                vs.fuel_tank_capacity_liters,
                vs.warranty_years,
                vs.image_url AS specification_image_url,
                vs.is_active AS specification_active,
                vd.chassis_number,
                vd.engine_number,
                vd.insurance_policy_number,
                vd.insurance_expiry_date,
                vd.emission_test_number,
                vd.emission_expiry_date,
                vd.permit_number,
                vd.permit_expiry_date,
                vd.warranty_expiry_date,
                vd.gps_tracking_id,
                vi.image_id AS vehicle_image_id,
                vi.image_url AS vehicle_image_url,
                vi.image_name AS vehicle_image_name,
                vi.description AS vehicle_image_description,
                vsi.image_id AS specification_image_id,
                vsi.image_url AS specification_image_url,
                vsi.image_name AS specification_image_name,
                vsi.description AS specification_image_description,
                (SELECT GROUP_CONCAT(
                    CONCAT_WS('|||',
                        usage_id,
                        COALESCE(package_id, ''),
                        COALESCE(tour_id, ''),
                        start_datetime,
                        COALESCE(end_datetime, ''),
                        COALESCE(start_odometer, ''),
                        COALESCE(end_odometer, ''),
                        COALESCE(route_description, ''),
                        COALESCE(purpose, ''),
                        COALESCE(fuel_used_liters, ''),
                        COALESCE(remarks, '')
                    ) ORDER BY start_datetime DESC SEPARATOR '||||'
                )
                FROM (
                    SELECT usage_id, package_id, tour_id, start_datetime, end_datetime,
                           start_odometer, end_odometer, route_description, purpose,
                           fuel_used_liters, remarks
                    FROM vehicle_usage_log
                    WHERE vehicle_id = v.vehicle_id
                    ORDER BY start_datetime DESC
                    LIMIT 10
                ) AS latest_usage) AS latest_usage_logs,
                vsh.service_id,
                vsh.service_date,
                vsh.service_center,
                vsh.service_type,
                vsh.odometer_reading AS service_odometer,
                vsh.cost AS service_cost,
                vsh.description AS service_description,
                vsh.next_service_due,
                vshi.image_url AS service_image_url,
                vshi.description AS service_image_description,
                vfr.fuel_record_id,
                vfr.refuel_date,
                vfr.fuel_type_id AS refuel_fuel_type_id,
                ft2.fuel_type_name AS refuel_fuel_type,
                vfr.quantity_liters,
                vfr.cost AS fuel_cost,
                vfr.odometer_reading AS fuel_odometer,
                vfr.refuel_station
            FROM vehicles v
            LEFT JOIN common_status cs ON v.status_id = cs.id
            LEFT JOIN vehicle_specifications vs ON v.specification_id = vs.specification_id
            LEFT JOIN transmission_types tt ON vs.transmission_type_id = tt.transmission_type_id
            LEFT JOIN fuel_types ft ON vs.fuel_type_id = ft.fuel_type_id
            LEFT JOIN air_conditioning_types ac ON vs.ac_type_id = ac.ac_type_id
            LEFT JOIN vehicle_details vd ON v.vehicle_id = vd.vehicle_id
            LEFT JOIN vehicle_images vi ON v.vehicle_id = vi.vehicle_id
            LEFT JOIN vehicle_specification_images vsi ON vs.specification_id = vsi.specification_id
            LEFT JOIN vehicle_service_history vsh ON v.vehicle_id = vsh.vehicle_id AND vsh.service_id = (
                SELECT MAX(service_id) FROM vehicle_service_history WHERE vehicle_id = v.vehicle_id
            )
            LEFT JOIN vehicle_service_images vshi ON vsh.service_id = vshi.service_id
            LEFT JOIN vehicle_fuel_records vfr ON v.vehicle_id = vfr.vehicle_id AND vfr.fuel_record_id = (
                SELECT MAX(fuel_record_id) FROM vehicle_fuel_records WHERE vehicle_id = v.vehicle_id
            )
            LEFT JOIN fuel_types ft2 ON vfr.fuel_type_id = ft2.fuel_type_id
            WHERE v.vehicle_id = ?
            """;

    public static final String GET_VEHICLE_ASSIGNMENTS = """
            SELECT 
                assignment_id,
                driver_id,
                start_date,
                end_date,
                purpose,
                remarks
            FROM vehicle_assignments 
            WHERE vehicle_id = ?
            ORDER BY start_date DESC
            LIMIT 10
            """;

    public static final String GET_VEHICLE_BASIC_DETAILS_BY_ID = """
            SELECT
            	v.vehicle_id,
                v.registration_number,
                vt.name AS type,
                vs.make,
                vs.model
            FROM vehicles v
            LEFT JOIN vehicle_type vt
            	ON v.vehicle_type_id = vt.vehicle_type_id
            LEFT JOIN vehicle_specifications vs
            	ON vs.specification_id = v.specification_id
            WHERE v.vehicle_id =?
            """;


}
