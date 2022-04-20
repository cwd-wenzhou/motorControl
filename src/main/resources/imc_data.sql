create database motor precision "ns";

CREATE TABLE `motor_data`
(`sample_time` TIMESTAMP,
`status_code` int,
`control_code` int,
`mode_code` int,
`error_code` int,
`reference_mechanical_position` int, 
`actual_mechanical_position` int, 
`reference_mechanical_speed` int, 
`actual_mechanical_speed` int, 
`reference_current_id` int, 
`actual_current_id` int, 
`reference_current_iq` int, 
`actual_current_iq` int, 
`u_current` int, 
`v_current` int, 
`voltage_vd` int, 
`voltage_vq` int, 
`position_ring_kp` int, 
`position_ring_kd` int, 
`speed_ring_kp` int, 
`speed_ring_ki` int, 
`current_ring_kp` int, 
`current_ring_ki` int);

