package com.felicita.queries;

public class WorkFlowQueries {

    public static final String GET_ALL_WORK_FLOW_DATA = """
            SELECT
            	wf.id AS WF_ID,
                wf.title AS WF_TITLE,
                wf.description AS WF_DESCRIPTION,
                wf.order_number AS WF_ORDER,
                wf.icon_url AS WF_ICON_URL,
                wf.icon_color AS WF_ICON_COLOR,
                wf.bg_color AS WF_BG_COLOR,
                wf.connect_text AS WF_CONNECT_TEXT,
                wf.link_url AS WF_LINK_URL,
                wfs.name AS WF_STATUS,
                cs.name AS WF_STATUS_STATUS,
            	wf.created_at AS WF_CREATED_AT,
                wf.created_by AS WF_CREATED_BY,
                wf.updated_at AS WF_UPDATED_AT,
                wf.updated_by AS WF_UPDATED_BY,
                wf.terminated_at AS WF_TERMINATED_AT,
                wf.terminated_by AS WF_TERMINATED_BY
            FROM work_flow wf
            LEFT JOIN work_flow_status wfs
            ON wf.work_flow_status_id = wfs.id
            LEFT JOIN common_status cs
            ON wfs.common_status_id = cs.id
            """;
}
