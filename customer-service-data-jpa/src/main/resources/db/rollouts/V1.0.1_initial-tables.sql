CREATE TABLE customers
(
    id                   UUID         NOT NULL DEFAULT public.uuid_generate_v4(),
    request_reference_id varchar(50)  NOT NULL,
    first_name           varchar(100) NOT NULL,
    last_name            varchar(100) NOT NULL,
    birth_date           DATE  NOT NULL,
    mobile_number        varchar(20)  NOT NULL,
    email                varchar(100) NOT NULL,
    created_date         TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_date         TIMESTAMPTZ  NOT NULL DEFAULT now(),

    CONSTRAINT customer_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX customer_unique_idx ON customers (first_name, last_name, mobile_number, email);
CREATE UNIQUE INDEX customer_mobile_number_idx ON customers (mobile_number);
CREATE UNIQUE INDEX customer_email_idx ON customers (email);
CREATE UNIQUE INDEX customer_created_date_idx ON customers (created_date);

GRANT INSERT, SELECT, UPDATE, DELETE ON customers TO "customers_apps"