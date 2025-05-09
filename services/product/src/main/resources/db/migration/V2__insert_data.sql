INSERT INTO category (description, name) VALUES
('Devices used for patient monitoring and diagnostics', 'Monitoring Equipment'),
('Equipment used in surgical procedures', 'Surgical Instruments'),
('Devices aiding mobility and rehabilitation', 'Mobility Aids'),
('Tools for medical imaging and diagnostics', 'Imaging Systems'),
('Emergency and life-saving equipment', 'Emergency & Life Support');

INSERT INTO product (description, name, available_quantity, price, category_id) VALUES
-- Monitoring Equipment
('Used for measuring heart rate and oxygen saturation', 'Pulse Oximeter', 100, 50.00, 1),
('Portable device for checking blood pressure levels', 'Digital Blood Pressure Monitor', 80, 120.00, 1),
('Wearable device to track heart rate and movement', 'ECG Smart Watch', 60, 250.00, 1),
('Measures blood glucose levels', 'Glucose Meter', 150, 75.00, 1),
('Used in hospitals for continuous patient monitoring', 'Multi-Parameter Patient Monitor', 40, 1500.00, 1),
('Device for tracking body temperature', 'Infrared Thermometer', 200, 40.00, 1),
('Compact home device for respiratory monitoring', 'Spirometer', 50, 300.00, 1),
('Smart wearable for tracking health metrics', 'Fitness Tracker', 90, 180.00, 1),
('Essential for monitoring fetal heart rate', 'Fetal Doppler', 30, 120.00, 1),
('Detects abnormal heart rhythms', 'Handheld ECG Monitor', 60, 220.00, 1),

-- Surgical Instruments
('Used for making precise incisions during surgery', 'Scalpel', 200, 15.00, 2),
('Hand tool for holding or manipulating tissue', 'Forceps', 180, 30.00, 2),
('Instrument for cutting sutures or tissue', 'Surgical Scissors', 150, 35.00, 2),
('Used for closing wounds during surgery', 'Suture Kit', 120, 50.00, 2),
('Essential tool for gripping surgical material', 'Hemostats', 100, 40.00, 2),
('Used for stabilizing and holding organs', 'Retractor', 80, 60.00, 2),
('Specialized instrument for orthopedic procedures', 'Bone Saw', 50, 500.00, 2),
('Used to secure surgical areas', 'Sterile Drapes', 200, 25.00, 2),
('High-precision cutting tool for surgeries', 'Electrosurgical Knife', 40, 600.00, 2),
('Handheld device for sealing tissues', 'Laparoscopic Stapler', 50, 900.00, 2),

-- Mobility Aids
('Walking aid for elderly or injured individuals', 'Walker', 120, 80.00, 3),
('Device for assisting mobility', 'Manual Wheelchair', 90, 250.00, 3),
('Motorized wheelchair for enhanced movement', 'Electric Wheelchair', 60, 1500.00, 3),
('Tool for assisting walking balance', 'Adjustable Cane', 200, 40.00, 3),
('Support system for people with mobility issues', 'Crutches', 150, 70.00, 3),
('Leg braces for walking support', 'Orthopedic Braces', 80, 120.00, 3),
('Helps in posture correction and balance', 'Standing Frame', 50, 350.00, 3),
('Facilitates recovery for injured legs', 'Knee Walker', 40, 450.00, 3),
('Provides movement support for injured arms', 'Arm Sling', 180, 30.00, 3),
('Specialized bed for patients with mobility limitations', 'Adjustable Hospital Bed', 40, 3000.00, 3),

-- Imaging Systems
('Standard medical imaging for bone injuries', 'X-Ray Machine', 30, 15000.00, 4),
('High-resolution imaging for diagnosing diseases', 'MRI Scanner', 10, 500000.00, 4),
('Used for scanning internal organs', 'Ultrasound Machine', 25, 20000.00, 4),
('Advanced imaging for brain activity analysis', 'CT Scanner', 15, 350000.00, 4),
('Specialized retinal imaging for eye diagnostics', 'Ophthalmic Tomography', 40, 18000.00, 4),
('Mobile device for bedside imaging', 'Portable X-Ray Machine', 20, 25000.00, 4),
('Laser system for tissue analysis', 'Fluorescence Imaging Device', 30, 40000.00, 4),
('Detects cancerous tissues', 'PET Scanner', 12, 400000.00, 4),
('Imaging device for monitoring blood flow', 'Doppler Ultrasound', 50, 2500.00, 4),
('Compact device for emergency imaging', 'Handheld Ultrasound Scanner', 40, 3500.00, 4),

-- Emergency & Life Support
('Device used for resuscitation during cardiac arrest', 'Automated External Defibrillator (AED)', 30, 1200.00, 5),
('Used for artificial respiration', 'Ventilator', 20, 30000.00, 5),
('Essential tool for oxygen supply', 'Oxygen Concentrator', 50, 1200.00, 5),
('Portable breathing aid for emergencies', 'Ambu Bag', 80, 40.00, 5),
('High-pressure mask for respiratory emergencies', 'CPAP Machine', 40, 1000.00, 5),
('Life-saving tool for paramedics', 'Emergency First Aid Kit', 200, 100.00, 5),
('Sterile tubing for IV fluid delivery', 'IV Infusion Set', 180, 15.00, 5),
('Critical device for cardiac patients', 'ECG Machine', 25, 5000.00, 5),
('Portable inhaler for breathing assistance', 'Nebulizer', 100, 80.00, 5),
('Blood transfusion equipment for emergency cases', 'Blood Warmer Device', 30, 1500.00, 5);
