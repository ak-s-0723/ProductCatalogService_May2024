ALTER TABLE test_model
    ADD num_field INT NULL;

ALTER TABLE test_model
    ADD text_field VARCHAR(255) NULL;

ALTER TABLE test_model
    MODIFY num_field INT NOT NULL;
